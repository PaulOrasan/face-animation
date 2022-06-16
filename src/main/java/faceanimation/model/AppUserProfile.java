package faceanimation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AppUserProfile {

    private String name;

    private String surname;

    private List<String> videos;
}
