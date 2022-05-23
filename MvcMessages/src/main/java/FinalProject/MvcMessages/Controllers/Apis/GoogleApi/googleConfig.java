package FinalProject.MvcMessages.Controllers.Apis.GoogleApi;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.MessagePort;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.Message;
import FinalProject.MvcMessages.Models.UserPerson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class googleConfig {


    private MessagePort mP = new MessagePort();


    private PersonPort pp = new PersonPort();

    private UserService uS = new UserService();
    public googleConfig() {
    }

    private Translate set() throws IOException {
   return TranslateOptions
            .newBuilder()
            .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("tokyo-charge-350513.json")))
            .build().getService();
  }


  public List<Message> translate(List<Message> messages) throws IOException, URISyntaxException {

      Translate gT = this.set();
      Translation translation;
      Detection detection;
      String languageDetected;


      UserPerson userSession = pp.getByUsername(uS.getSessionUsername());
      String languageUser = userSession.getLanguage().getCode();

      if (messages!=null) {
          for (Message m : messages) {

              detection = gT.detect(m.getContent());
              languageDetected = detection.getLanguage();

              if (!languageUser.equals(languageDetected)) {

                  translation = gT.translate(m.getContent(),
                          Translate.TranslateOption.sourceLanguage(languageDetected),
                          Translate.TranslateOption.targetLanguage(languageUser));
                  m.setContent(translation.getTranslatedText());

              }
          }
      }
      return messages;
  }
}
