package com.petroli.gestionefasipetroli.entities;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static com.petroli.gestionefasipetroli.utils.ConvertitoreRuoli.convertiinruolo;

public class UtenteDettaglio extends Utente implements UserDetails {

    public UtenteDettaglio(final Utente users) {
        super(users);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        int ruolo = getRuolo();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(convertiinruolo(ruolo));

        List<GrantedAuthority> listaautorita = new LinkedList<>();
        listaautorita.add(simpleGrantedAuthority);

        return listaautorita;

    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}