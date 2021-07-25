package com.book.store.controller;

import com.book.store.controller.model.OrderRowRequest;
import com.book.store.dto.CartDto;
import com.book.store.dto.Converter;
import com.book.store.dto.ShippingMethodDto;
import com.book.store.dto.builder.shipping.ShippingBuilder;
import com.book.store.dto.builder.shipping.ShippingMethodDtoBuilder;
import com.book.store.dto.builder.shipping.ShippingMethodDtoDirector;
import com.book.store.model.Cart;
import com.book.store.model.singleton.DhlSingleton;
import com.book.store.model.singleton.InpostSingleton;
import com.book.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<?> saveCart(Principal principal, @RequestBody @Valid OrderRowRequest orderRowRequest) {
        Cart cart = this.cartService.saveCart(principal.getName(), orderRowRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<CartDto> getCart(Principal principal) {
        Cart cart = this.cartService.getCart(principal.getName());
        return new ResponseEntity<>(Converter.cartToCartDto(cart), HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<?> deleteCart(Principal principal, @RequestParam final Long orderRowId) {
        this.cartService.deleteCart(principal.getName(), orderRowId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cart/shipping")
    public ResponseEntity<List<ShippingMethodDto>> getShippingMethods() {
        ShippingMethodDtoDirector shippingMethodDtoDirector = new ShippingMethodDtoDirector();
        ShippingMethodDtoBuilder shippingBuilder = new ShippingMethodDtoBuilder();
        shippingMethodDtoDirector.constructShippingMethod(shippingBuilder, InpostSingleton.getInstance().getName(), InpostSingleton.getInstance().getPrice());

        ShippingMethodDto inpost = shippingBuilder.build();
        shippingMethodDtoDirector.constructShippingMethod(shippingBuilder, DhlSingleton.getInstance().getName(), DhlSingleton.getInstance().getPrice());
        ShippingMethodDto dhl = shippingBuilder.build();

        List<ShippingMethodDto> shippingMethodDtos = new ArrayList<>();
        shippingMethodDtos.add(inpost);
        shippingMethodDtos.add(dhl);

        return new ResponseEntity<>(shippingMethodDtos, HttpStatus.OK);
    }

}
