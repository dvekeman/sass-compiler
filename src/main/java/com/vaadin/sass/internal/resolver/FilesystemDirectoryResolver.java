/*
 * Copyright 2000-2014 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.sass.internal.resolver;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.w3c.css.sac.InputSource;

public class FilesystemDirectoryResolver extends FilesystemResolver {

    @Override
    public InputSource resolveNormalized(String identifier) {
        InputSource resolved = super.resolveNormalized(identifier);
        if(resolved != null){
           return resolved;
        }
        return resolveNormalizedDirectory(identifier);
    }

    public InputSource resolveNormalizedDirectory(String identifier) {
        if (!Files.isDirectory(Paths.get(identifier))) {
            return null;
        }
        InputSource source = new InputSource();
        source.setURI(identifier);
        return source;
    }

}
