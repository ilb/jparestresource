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
package ru.ilb.jparestresource.web;

import java.io.InputStream;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import ru.ilb.common.jaxb.util.JaxbUtil;
import ru.ilb.jparestresource.api.DocumentsResource;
import ru.ilb.jparestresource.view.Document;
import ru.ilb.jparestresource.view.Documents;

/**
 *
 * @author slavb
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class DocumentsResourceImplTest {

    @LocalServerPort
    private Integer randomPort;

    @Inject
    private DocumentsResourceSupport documentsResourceSupport;

    private final JAXBContext jaxbContext;

    public DocumentsResourceImplTest() {
        try {
            jaxbContext = JAXBContext.newInstance("ru.ilb.jparestresource.view");
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testCreate() {
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream("test/createDocumentXml.xml");
        Document document = JaxbUtil.unmarshal(jaxbContext, new StreamSource(resource), Document.class, "application/xml");
        DocumentsResource instance = documentsResourceSupport.getDocumentsResource(randomPort);
        instance.create(document);
    }

    /**
     * Test of list method, of class DocumentsResourceImpl.
     */
    @Test
    @Order(999)
    public void testList() {
        System.out.println("list");
        Integer limit = null;
        String order = "";
        DocumentsResource instance = documentsResourceSupport.getDocumentsResource(randomPort);
        Documents result = instance.list(limit, order);
        assertTrue(result.getDocuments().size() > 0);
    }

}
