/*
 * Copyright 2019 slavb.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.ilb.jparestresource.web;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.xml.XSLTTransform;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import ru.ilb.jparestresource.api.DocumentResource;
import ru.ilb.jparestresource.logic.DocumentLogic;
import ru.ilb.jparestresource.mappers.DocumentMapper;
import ru.ilb.jparestresource.view.Document;

@Configurable(preConstruction = true, dependencyCheck = true, autowire = Autowire.BY_NAME)
@Transactional
public class DocumentResourceImpl implements DocumentResource {

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private DocumentLogic documentLogic;

    private final long documentId;
    private final ApplicationContext applicationContext;
    private final MessageContext messageContext;

    public DocumentResourceImpl(long documentId, ApplicationContext applicationContext, MessageContext messageContext) {
        //не работает @Autowired в @Configurable TEMP FIX. autowire вручную
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        this.documentId = documentId;
        this.applicationContext = applicationContext;
        this.messageContext = messageContext;
    }

    @Override
    @XSLTTransform(value = "stylesheets/jparestresource/document.xsl", mediaTypes = "application/xhtml+xml", type = XSLTTransform.TransformType.SERVER)
    public Document find() {
        return documentMapper.createFromEntity(documentLogic.getDocument(documentId));
    }

    @Override
    public void edit(Document document) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
