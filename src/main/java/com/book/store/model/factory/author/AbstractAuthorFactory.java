package com.book.store.model.factory.author;

import com.book.store.controller.model.AuthorRequest;
import com.book.store.model.Author;

public abstract class AbstractAuthorFactory {

    public abstract Author createAuthor(AuthorRequest authorRequest);

}
