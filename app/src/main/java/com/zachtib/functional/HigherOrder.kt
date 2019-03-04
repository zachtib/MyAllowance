package com.zachtib.functional

import com.zachtib.functional.types.Either

fun <A, B> List<A>.unfurl(getChildren: A.() -> List<B>): List<Either<A, B>> {
    val list = mutableListOf<Either<A, B>>()
    for (parent in this) {
        list.add(Either.Left(parent))
        for (child in parent.getChildren()) {
            list.add(Either.Right(child))
        }
    }
    return list
}