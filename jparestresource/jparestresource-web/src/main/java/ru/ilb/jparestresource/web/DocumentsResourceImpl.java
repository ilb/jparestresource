/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ilb.jparestresource.web;

import io.swagger.annotations.Api;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ilb.jparestresource.api.DocumentResource;
import ru.ilb.jparestresource.api.DocumentsResource;
import ru.ilb.jparestresource.logic.DocumentLogic;
import ru.ilb.jparestresource.mappers.DocumentMapper;
import ru.ilb.jparestresource.utils.JaxbHelper;
import ru.ilb.jparestresource.repositories.DocumentRepository;
import ru.ilb.jparestresource.providers.AuthorizationHandler;
import ru.ilb.jparestresource.view.Document;
import ru.ilb.jparestresource.view.Documents;

@Path("documents")
public class DocumentsResourceImpl implements DocumentsResource, ContextResource {

    @Autowired
    AuthorizationHandler authorizationHandler;

    @Autowired
    JaxbHelper jaxbHelper;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private DocumentLogic documentLogic;

    private UriInfo uriInfo;

    @Autowired
    private ApplicationContext applicationContext;

    private MessageContext messageContext;

    @Context
    @Override
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @Override
    @Context
    public void setMessageContext(MessageContext messageContext) {
        this.messageContext = messageContext;
    }

    @Autowired
    DocumentRepository documentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(DocumentsResourceImpl.class);

    @Override
    @Transactional
    public Documents list(Integer limit, String order) {
        return documentMapper.createWrapperFromEntities(documentRepository.findAll());
    }

    @Override
    @Transactional
    public long create(Document document) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void createBatch(Documents documents) {
        documentRepository.saveAll(documentMapper.createFromDtos(documents.getDocuments()));
    }

    @Override
    public DocumentResource getDocumentResource(long documentId) {
        return new DocumentResourceImpl(documentId, applicationContext, messageContext);
    }

}
