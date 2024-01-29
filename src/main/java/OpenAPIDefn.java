import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name="Quarkus TODO app",
                        description="This API manages TO-DO tasks")
        },
        info = @Info(
                title="Open API and Swagger Demo",
                version = "1.0.1",
                contact = @Contact(
                        name = "Global Tpp @Lloyds Bank UK",
                        url = "http://www.localhost:8080",
                        email = "suppoert@GttpLBG.com"),
                license = @License(
                        name = "AnyLicense 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class OpenAPIDefn extends Application {
}
