package com.book.store.dto;

import com.book.store.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private String email;
    private Role role;
    private Collection<AddressDetails> addresses;
}
