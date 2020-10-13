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

import java.util.Arrays;
import javax.inject.Named;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import ru.ilb.jparestresource.api.DocumentsResource;

/**
 *
 * @author slavb
 */
@Named
public class DocumentsResourceSupport {

    private DocumentsResource resource;


    //@Inject
    private MOXyJsonProvider jsonProvider = new MOXyJsonProvider();

//    @Inject
//    private JsonMapObjectProvider jsonMapObjectProvider;


    public DocumentsResource getDocumentsResource(Integer randomPort) {
        if (resource == null) {
            String port = randomPort.toString();
            String resourceUri = "http://localhost:" + port + "/web";
            System.out.println("resourceUri=" + resourceUri);
            resource = JAXRSClientFactory.create(resourceUri, DocumentsResource.class,
                    Arrays.asList(jsonProvider)); // jsonProvider,jsonMapObjectProvider

            configureTimeout(resource, 1000 * 300);
        }
        return resource;

    }

    /**
     * Настройка таймаута чтобы посидеть в отладчике
     *
     * @param resource
     * @param receiveTimeout
     */
    private static void configureTimeout(Object resource, int receiveTimeout) {
        WebClient webClient = WebClient.fromClient(WebClient.client(resource));

        HTTPConduit conduit = WebClient.getConfig(webClient).getHttpConduit();
        //conduit.getClient().setConnectionTimeout(1000 * 3);
        conduit.getClient().setReceiveTimeout(receiveTimeout);

    }
}
