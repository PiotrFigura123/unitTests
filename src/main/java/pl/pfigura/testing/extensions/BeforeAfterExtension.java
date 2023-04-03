package pl.pfigura.testing.extensions;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BeforeAfterExtension implements BeforeEachCallback, AfterAllCallback {
    @Override
    public void afterAll(final ExtensionContext extensionContext) throws Exception {
        System.out.println("inside after each extension");
    }

    @Override
    public void beforeEach(final ExtensionContext extensionContext) throws Exception {

        System.out.println("inside Before each extension");
    }
}
