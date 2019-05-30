/*
 * Copyright 2016 slavb.
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
package ru.ilb.jparestresource.utils;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.transform.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ilb.common.jaxb.util.JaxbUtil;

/**
 *
 * @author slavb
 */
@Component
public class JaxbHelper {

    @Autowired
    private ContextResolver<JAXBContext> jaxbContextResolver;

    /**
     * unmarshalls object instance
     *
     * @param <T>
     * @param source example from String: new StreamSource(new
     * java.io.StringReader(string)), from InputStream: new StreamSource(is)
     * @param type
     * @param mediaType
     * @return T
     */
    public <T> T unmarshal(Source source, Class<T> type, String mediaType) {

        JAXBContext jaxbContext = jaxbContextResolver.getContext(type);
        return JaxbUtil.unmarshal(jaxbContext, source, type, mediaType);
    }

    /**
     *
     * @param <T>
     * @param data
     * @param type
     * @param mediaType
     * @return T
     */
    public <T> T unmarshal(String data, Class<T> type, String mediaType) {

        JAXBContext jaxbContext = jaxbContextResolver.getContext(type);
        return JaxbUtil.unmarshal(jaxbContext, data, type, mediaType);
    }


    public String marshal(Object obj, String mediaType) {
        JAXBContext jaxbContext = jaxbContextResolver.getContext(obj.getClass());
        return JaxbUtil.marshal(jaxbContext, obj, mediaType);
    }


}
