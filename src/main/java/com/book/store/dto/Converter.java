package com.book.store.dto;

import com.book.store.dto.builder.CategoryDtoBuilder;
import com.book.store.dto.builder.CategoryDtoDirector;
import com.book.store.dto.builder.author.AuthorDtoBuilder;
import com.book.store.dto.builder.author.AuthorDtoDirector;
import com.book.store.dto.builder.product.ProductDtoBuilder;
import com.book.store.dto.builder.product.ProductDtoDirector;
import com.book.store.model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static User signUpToUser(Credentials credentials) {
        User user = new User();
        user.setEmail(credentials.getEmail());
        user.setPassword(credentials.getPassword());

        return user;
    }

    public static ProductDto productToProductDto(Product product) {
        ProductDtoDirector productDtoDirector = new ProductDtoDirector();
        ProductDtoBuilder productBuilder = new ProductDtoBuilder();
        productDtoDirector.constructProduct(productBuilder, product);

        ProductDto productDto = productBuilder.build();
        return productDto;
    }

    public static AuthorDto authorToAuthorDto(Author author) {
        AuthorDtoDirector authorDtoDirector = new AuthorDtoDirector();
        AuthorDtoBuilder authorBuilder = new AuthorDtoBuilder();
        authorDtoDirector.constructAuthor(authorBuilder, author);

        AuthorDto authorDto = authorBuilder.build();

        return authorDto;
    }

    public static List<AuthorDto> authorListToAuthorDtoList(List<Author> authors) {
        List<AuthorDto> authorDtos = authors.stream().map(Converter::authorToAuthorDto).collect(Collectors.toList());
        return authorDtos;
    }

    public static CategoryDto categoryToCategoryDto(Category category) {
        CategoryDtoDirector categoryDtoDirector = new CategoryDtoDirector();
        CategoryDtoBuilder categoryDtoBuilder = new CategoryDtoBuilder();
        categoryDtoDirector.constructCategory(categoryDtoBuilder, category);

        CategoryDto categoryDto = categoryDtoBuilder.build();

        return categoryDto;
    }

    public static List<CategoryDto> categoryListToCategoryListDto(List<Category> categories) {
        List<CategoryDto> categoryDtos = categories.stream().map(Converter::categoryToCategoryDto).collect(Collectors.toList());
        return categoryDtos;
    }

    public static UserDetails userToUserDetails(User user) {
        return UserDetails.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .addresses(addressesToAddressDetailsList(user.getAddresses()))
                .build();
    }

    private static List<AddressDetails> addressesToAddressDetailsList(Collection<Address> addressList) {
        if (addressList == null) {
            return Collections.emptyList();
        }
        return addressList
                .stream()
                .map(Converter::toAddressDetails).collect(Collectors.toList());
    }

    public static AddressDetails toAddressDetails(Address address) {
        return AddressDetails.builder()
                .city(address.getCity())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .buildingNumber(address.getBuildingNumber())
                .build();
    }

    public static CartDto cartToCartDto(Cart cart) {
        return CartDto.builder()
                .orderRowDtos(orderRowListToOrderRowDtoList(cart.getOrderRows()))
                .build();
    }

    public static List<OrderRowDto> orderRowListToOrderRowDtoList(List<OrderRow> orderRow) {
        List<OrderRowDto> orderRowDtos = orderRow.stream().map(Converter::orderRowToOrderRowDto).collect(Collectors.toList());
        return orderRowDtos;
    }

    public static OrderRowDto orderRowToOrderRowDto(OrderRow orderRow) {
        return OrderRowDto.builder()
                .orderRowId(orderRow.getId())
                .productId(orderRow.getId())
                .productDto(productToProductDto(orderRow.getProduct()))
                .productQuantity(orderRow.getQuantity())
                .build();
    }

    public static List<OrderDto> orderToOrderDto(final List<Order> orders) {
        List<OrderDto> orderDtos = orders.stream().map(order -> {
            return OrderDto.builder()
                    .price(order.getPrice())
                    .orderItem(orderItemListOrderItemDtoList(order.getOrderItems()))
                    .trackingNumber(order.getTrackingNumber())
                    .shippingMethod(order.getShippingMethod())
                    .shippingPrice(order.getShippingPrice())
                    .build();
        }).collect(Collectors.toList());

        return orderDtos;
    }

    public static List<OrderItemDto> orderItemListOrderItemDtoList(final List<OrderItem> orderItems) {
        List<OrderItemDto> orderItemDtoList = orderItems.stream().map(orderItem -> {
            return orderItemToOrderItemDto(orderItem);
        }).collect(Collectors.toList());

        return orderItemDtoList;
    }

    public static OrderItemDto orderItemToOrderItemDto(final OrderItem orderItem) {
        return OrderItemDto.builder()
                .name(orderItem.getName())
                .price(orderItem.getPrice())
                .build();
    }

    public static List<ProductDto> productListToProductDtoList(final List<Product> products) {
        List<ProductDto> productDtos = products.stream().map(Converter::productToProductDto).collect(Collectors.toList());
        return productDtos;
    }

}
