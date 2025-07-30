package it.aces.vlad_project.util;

import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPage {
    private int currentPage;
    private int lastPage;
    private int perPage;
    private long total;

    public static CustomPage fromPage(Page<?> page) {
        return CustomPage.builder()
                .currentPage(page.getNumber() + 1)
                .lastPage(page.getTotalPages())
                .perPage(page.getSize())
                .total(page.getTotalElements())
                .build();
    }
}
