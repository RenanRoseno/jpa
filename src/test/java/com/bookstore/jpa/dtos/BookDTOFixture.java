package com.bookstore.jpa.dtos;

import java.util.Set;
import java.util.UUID;

public class BookDTOFixture {
    public static BookRecordDTO build(String title,
                               UUID publisherId,
                               Set<UUID> authorIds,
                               String reviewComment) {
        return new BookRecordDTO(title, publisherId, authorIds, reviewComment);
    }
}
