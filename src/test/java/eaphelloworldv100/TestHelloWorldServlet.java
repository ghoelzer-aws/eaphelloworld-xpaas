/**
 * 
 */
package eaphelloworldv100;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstarts.helloworld.HelloService;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * @author ghoelzer
 *
 */

@RunWith(Arquillian.class)
public class TestHelloWorldServlet {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(HelloService.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Inject
    HelloService greeter;

    @Test
    public void should_create_greeting() {
        Assert.assertEquals("Hello Earthling! Version 1.01",
            greeter.createHelloMessage("Earthling"));
        System.out.println(greeter.createHelloMessage("Earthling"));
    }

}
