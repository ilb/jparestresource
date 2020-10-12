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

import javax.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import ru.ilb.jparestresource.api.DocumentsResource;
import ru.ilb.jparestresource.view.Documents;

/**
 *
 * @author slavb
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentsResourceImplTest {
 @LocalServerPort
    private Integer randomPort;

    @Inject
    private DocumentsResourceSupport documentsResourceSupport;

    public DocumentsResourceImplTest() {
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


    /**
     * Test of list method, of class DocumentsResourceImpl.
     */
    @Test
    public void testList() {
        System.out.println("list");
        Integer limit = null;
        String order = "";
        DocumentsResource instance = documentsResourceSupport.getDocumentsResource(randomPort);
        Documents result = instance.list(limit, order);
        assertEquals(9, result.getDocuments().size());
    }


}
