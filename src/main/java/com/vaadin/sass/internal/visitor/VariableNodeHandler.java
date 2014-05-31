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

package com.vaadin.sass.internal.visitor;

import com.vaadin.sass.internal.ScssStylesheet;
import com.vaadin.sass.internal.parser.LexicalUnitImpl;
import com.vaadin.sass.internal.parser.SCSSLexicalUnit;
import com.vaadin.sass.internal.parser.SassListItem;
import com.vaadin.sass.internal.tree.VariableNode;

public class VariableNodeHandler {

    public static void traverse(VariableNode node) {
        VariableNode variable = ScssStylesheet.getVariable(node.getName());
        if (!node.isGuarded() || variable == null || variable.getExpr() == null) {
            ScssStylesheet.addVariable(node);
        } else { // Handle the case where a variable has the value SCSS_NULL
            SassListItem value = variable.getExpr();
            if (value instanceof LexicalUnitImpl) {
                LexicalUnitImpl unit = (LexicalUnitImpl) value;
                if (unit.getLexicalUnitType() == SCSSLexicalUnit.SCSS_NULL) {
                    ScssStylesheet.addVariable(node);
                }
            }
        }
        node.removeFromParent();
    }
}