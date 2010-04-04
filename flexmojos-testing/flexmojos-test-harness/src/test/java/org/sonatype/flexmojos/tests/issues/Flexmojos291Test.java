package org.sonatype.flexmojos.tests.issues;

import org.testng.annotations.Test;

public class Flexmojos291Test
    extends AbstractIssueTest
{

    @Test
    public void classifier()
        throws Exception
    {
        testIssue( "flexmojos-291" );
    }
}
