/*
 * Copyright 2017 slavb.
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
package ru.ilb.jparestresource.logic;

import com.jcabi.aspects.Loggable;
import javax.inject.Inject;
import javax.inject.Named;
import ru.ilb.jparestresource.model.Document;
import ru.ilb.jparestresource.repositories.DocumentRepository;

/**
 *
 * @author slavb
 */
@Named
public class DocumentFactory {

    private final DocumentRepository documentRepository;

    @Inject
    public DocumentFactory(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }


    @Loggable(Loggable.INFO)
    public Document getDocument(long documentId) {
        return documentRepository.getOne(documentId);
    }

}
