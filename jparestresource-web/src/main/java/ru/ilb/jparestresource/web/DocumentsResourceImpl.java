/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ilb.jparestresource.web;

import com.github.sadstool.redissonaspectlock.annotation.Lockable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.transaction.annotation.Transactional;
import ru.ilb.common.jaxrs.async.AsyncTaskManager;
import ru.ilb.jparestresource.api.DocumentResource;
import ru.ilb.jparestresource.api.DocumentsResource;
import ru.ilb.jparestresource.logic.DocumentFactory;
import ru.ilb.jparestresource.mappers.DocumentMapper;
import ru.ilb.jparestresource.usecases.CreateBatch;
import ru.ilb.jparestresource.usecases.ListDocuments;
import ru.ilb.jparestresource.view.Document;
import ru.ilb.jparestresource.view.Documents;

@Named
public class DocumentsResourceImpl implements DocumentsResource { //, ContextResource

//    private static final Logger LOG = LoggerFactory.getLogger(DocumentsResourceImpl.class);

    @Inject
    private DocumentMapper documentMapper;
    @Inject
    private DocumentFactory documentFactory;

    @Inject
    private AsyncTaskManager asyncTaskManager;

    @Inject
    private CreateBatch createBatch;

    @Inject
    private ListDocuments listDocuments;

    private UriInfo uriInfo;


    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }
//  private MessageContext messageContext;
//
//    @Context
//    public void setMessageContext(MessageContext messageContext) {
//        this.messageContext = messageContext;
//    }

    @Override
    @Transactional
    public Documents list(Integer limit, String order) {
        return documentMapper.createWrapperFromEntities(listDocuments.execute(limit, order));
    }

    @Override
    @Transactional
    public long create(Document document) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Lockable
    @Transactional
    public void createBatch(Documents documents) {
        createBatch.execute(documentMapper.createFromDtos(documents.getDocuments()));
    }

    @Override
    public DocumentResource getDocumentResource(long documentId) {
        return new DocumentResourceImpl(documentMapper, documentFactory, documentId);
    }

    @Override
    public Response createBatchAsyncSubmit(Documents documents) {
        return asyncTaskManager.submit(() -> {
            createBatch(documents);
            return null;
        }, uriInfo);
    }

    @Override
    public Response createBatchAsyncWait(String taskId, String i) {
        return asyncTaskManager.wait(taskId, uriInfo, i);
    }

}
