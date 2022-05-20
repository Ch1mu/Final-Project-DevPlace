package FinalProject.MvcMessages.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @NotNull
    private long idChat;
    private String name;
    private boolean isGroup;

}
