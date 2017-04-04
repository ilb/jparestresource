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
package ru.ilb.jparestresource.persistance;

import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;
import org.eclipse.persistence.config.DescriptorCustomizer;

/**
 *
 * @author slavb
 */
public class HistoryCustomizer implements DescriptorCustomizer {

    @Override
    public void customize(ClassDescriptor descriptor) {
        HistoryPolicy policy = new HistoryPolicy();
        String primaryTable = descriptor.getTableName();
        policy.addStartFieldName(primaryTable + ".ROW_START");
        policy.addEndFieldName(primaryTable + ".ROW_END");
        policy.addHistoryTableName(primaryTable, primaryTable + "_HIST");
        descriptor.setHistoryPolicy(policy);
    }
}