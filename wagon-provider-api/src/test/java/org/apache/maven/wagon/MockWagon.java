package org.apache.maven.wagon;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
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

import org.apache.maven.wagon.authorization.AuthorizationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author <a href="mailto:jason@maven.org">Jason van Zyl</a>
 * @version $Id$
 */
public class MockWagon
    extends StreamWagon
{
    private boolean errorInputStream;

    public MockWagon()
    {
    }

    public MockWagon( boolean errorInputStream )
    {
        this.errorInputStream = errorInputStream;
    }


    public void fillInputData( InputData inputData )
        throws TransferFailedException
    {

        InputStream is;

        if ( errorInputStream )
        {
            MockInputStream mockInputStream = new MockInputStream();

            mockInputStream.setForcedError( true );

            is = mockInputStream;

        }
        else
        {
            byte[] buffer = new byte[1024 * 4 * 5];

            is = new ByteArrayInputStream( buffer );
        }

        inputData.setInputStream( is );

    }

    public void fillOutputData( OutputData outputData )
        throws TransferFailedException
    {

        OutputStream os;

        if ( errorInputStream )
        {
            MockOutputStream mockOutputStream = new MockOutputStream();

            mockOutputStream.setForcedError( true );

            os = mockOutputStream;
        }
        else
        {
            os = new ByteArrayOutputStream();
        }

        outputData.setOutputStream( os );

    }

    public boolean getIfNewer( String resourceName, File destination, long timestamp )
        throws TransferFailedException, ResourceDoesNotExistException, AuthorizationException
    {
        return false;
    }

    public void openConnection()
    {
    }

    public void closeConnection()
    {
    }


}