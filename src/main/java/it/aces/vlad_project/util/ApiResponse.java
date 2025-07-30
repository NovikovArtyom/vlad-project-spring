package it.aces.vlad_project.util;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private List<T> data;
    private CustomPage meta;

    public static <T> ApiResponse<T> of(Page<T> page) {
        return new ApiResponse<>(page.getContent(), CustomPage.fromPage(page));
    }
}
