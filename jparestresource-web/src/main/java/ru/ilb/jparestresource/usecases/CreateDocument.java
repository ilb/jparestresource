/*
 * Copyright 2020 slavb.
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
package ru.ilb.jparestresource.usecases;

import javax.inject.Named;
import ru.ilb.jparestresource.model.Document;
import ru.ilb.jparestresource.repositories.DocumentRepository;

/**
 * Пакетное создание документов
 *
 * @author slavb
 */
@Named
public class CreateDocument {

    private final DocumentRepository documentRepository;

    public CreateDocument(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document execute(Document document) {
        return documentRepository.save(document);
    }

}
