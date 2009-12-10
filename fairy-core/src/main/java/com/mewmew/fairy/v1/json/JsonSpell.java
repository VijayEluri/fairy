/*
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/
package com.mewmew.fairy.v1.json;

import com.mewmew.fairy.v1.cli.Param;
import com.mewmew.fairy.v1.cli.StringParser;
import com.mewmew.fairy.v1.pipe.Output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public abstract class JsonSpell extends BaseJsonSpell
{
    private JsonOutput jsonOutput;

    @Param(option = "O", name = "format", desc = "PRETTY, LINE, COMPACT", defaultValue="LINE")
    private OutputFormat outputFormat;

    @Override
    protected Output<Map<String, Object>> createOutput(OutputStream out) throws IOException
    {
        return JsonOutput.createOutput(out, outputFormat);
    }

    @Override
    public StringParser[] getParsers()
    {
        return new StringParser[]{new StringParser<OutputFormat>()
        {
            public OutputFormat parse(String v)
            {
                OutputFormat f = null;
                try {
                    return OutputFormat.valueOf(v);
                }
                catch (IllegalArgumentException e) {
                    return OutputFormat.COMPACT;
                }
            }
        }};
    }
}
