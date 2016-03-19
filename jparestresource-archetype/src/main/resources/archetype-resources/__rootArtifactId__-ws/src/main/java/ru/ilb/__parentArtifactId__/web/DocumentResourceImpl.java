#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${groupId}.web;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Path;
import org.apache.cxf.annotations.SchemaValidation;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.ilb.meta.${parentArtifactId}.document.Document;
import ru.ilb.meta.${parentArtifactId}.document.Documents;
import ${groupId}.api.DocumentsResource;
import ru.ilb.meta.${parentArtifactId}.document.ReadOptionsType;

@SchemaValidation(schemas = "schemas/${parentArtifactId}/document.xsd")
@Path("documents")
public class DocumentResourceImpl implements DocumentsResource {

    @PersistenceContext(unitName = "${parentArtifactId}")
    private EntityManager em;

    @Autowired
    EntityManagerIntr entityManagerIntr;

    @Override
    @Transactional
    public Documents list(List<ReadOptionsType> options) {
        Documents res = new Documents();

        TypedQuery<Document> query = em.createQuery("SELECT d FROM Document d", Document.class);
        if(options!=null){
            if(options.contains(ReadOptionsType.WITH_DOCFILES)){
                query.setHint(QueryHints.LEFT_FETCH, "d.docfiles");
            }
        }
        List<Document> result = query.getResultList();
        //res.getDocuments().addAll(entityManagerIntr.copy(result, null, null));
        res.getDocuments().addAll(result);
        return res;
    }

    @Override
    @Transactional
    public Document find(UUID uid) {
        TypedQuery<Document> query = em.createNamedQuery("Document.byUid", Document.class);
        query.setParameter("uid", uid);
        Document doc = query.getSingleResult();
        return doc;
    }

    @Override
    @Transactional
    public UUID create(Document document) {
        em.persist(document);
        return document.getUid();
    }

    @Override
    @Transactional
    public void edit(UUID uid, Document document) {
        Document doc = find(uid);
        document.copyTo(doc);
    }

    @Override
    @Transactional
    public void remove(UUID uid) {
        Document doc = find(uid);
        em.remove(doc);
    }

}
