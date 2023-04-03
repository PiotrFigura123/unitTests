package pl.pfigura.testing.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class IAException implements TestExecutionExceptionHandler {


    @Override
    public void handleTestExecutionException(final ExtensionContext extensionContext, final Throwable throwable) throws Throwable {
      if(throwable instanceof IllegalArgumentException){
          System.out.println("Just ignoreed IllegalArguException");
      }else{
          throw throwable;
      }
    }
}
