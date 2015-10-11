/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package doc.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import doc.beo.JnuDocument;
import doc.commons.Constant;

/**
 * Get the List of presidents, as well as the List of presidents that were
 * selected.
 * 
 * @author Jeff Johnston
 */
public class SelectedDocsUtils 
{
    public static Collection saveSelectedDocsIDs(HttpServletRequest request) 
    {
    	//System.out.println("start");
        Collection documents = (Collection) request.getSession().getAttribute(Constant.SELECTED_PRESIDENTS);
//System.out.println(documents);
        if (documents == null) {
            documents = new ArrayList();
            request.getSession().setAttribute(Constant.SELECTED_PRESIDENTS, documents);
        }

        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = (String) parameterNames.nextElement();
            if (parameterName.startsWith("chkbx_")) {
                String docId = StringUtils.substringAfter(parameterName, "chkbx_");
                String parameterValue = request.getParameter(parameterName);
                if (parameterValue.equals(Constant.SELECTED)) {
                    if (!documents.contains(docId)) {
                        documents.add(docId);
                    }
                } else {
                    documents.remove(docId);
                }
            }
        }

        return documents;
    }

    public static Collection getSelectedDocuments(Collection documents, Collection selectedDocumentsIds) {
        Collection result = new ArrayList();

        for (Iterator iter = selectedDocumentsIds.iterator(); iter.hasNext();) {
            String selectedPresident = (String) iter.next
            ();
            result.add(getDocument(documents, selectedPresident));
        }

        return result;
    }

    private static JnuDocument getDocument(Collection presidents, String selectedPresident) {
        for (Iterator iter = presidents.iterator(); iter.hasNext();) {
            JnuDocument doc = (JnuDocument) iter.next();
            Integer documentId = doc.getId();
            if (documentId.toString().equals(selectedPresident)) {
                return doc;
            }
        }
        return null;
    }
}