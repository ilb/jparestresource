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

import org.apache.cxf.jaxrs.ext.xml.XSLTTransform;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;
import ru.ilb.jparestresource.api.DocumentResource;
import ru.ilb.jparestresource.logic.DocumentFactory;
import ru.ilb.jparestresource.mappers.DocumentMapper;
import ru.ilb.jparestresource.view.Document;

@Configurable(preConstruction = true, dependencyCheck = true, autowire = Autowire.BY_NAME)
@Transactional
public class DocumentResourceImpl implements DocumentResource {

    private final DocumentMapper documentMapper;

    private final DocumentFactory documentFactory;

    private final long documentId;

    public DocumentResourceImpl(DocumentMapper documentMapper, DocumentFactory documentFactory, long documentId) {
        this.documentMapper = documentMapper;
        this.documentFactory = documentFactory;
        this.documentId = documentId;
    }

    @Override
    @XSLTTransform(value = "stylesheets/jparestresource/document.xsl", mediaTypes = "application/xhtml+xml",
            type = XSLTTransform.TransformType.SERVER)
    public Document find() {
        return documentMapper.createFromEntity(documentFactory.getDocument(documentId));
    }

    @Override
    public void edit(Document document) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
