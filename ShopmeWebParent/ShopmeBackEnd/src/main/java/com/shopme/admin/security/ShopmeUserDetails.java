package com.shopme.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

public class ShopmeUserDetails implements UserDetails {
	
	private User user;
	

	public ShopmeUserDetails(User user) {
		System.out.println("ShopmeUserDetails Constructor::::::::::::::::::::");
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("getAuthorities::::::::::::::::::::In ShopmeUserDetails");
		Set<Role> roles=user.getRoles();
		List<SimpleGrantedAuthority> authorites=new ArrayList<>();
		for(Role role: roles) {
			authorites.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorites;
	}

	@Override
	public String getPassword() {
		System.out.println("getPassword::::::::::::::::::::In ShopmeUserDetails");
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		System.out.println("getUsername::::::::::::::::::::In ShopmeUserDetails");
		return user.getEmail();
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
		
		return user.isEnabled();
	}
	public String getFullName() {
		return this.user.getFirstName()+" "+this.user.getLastName();
	}

}