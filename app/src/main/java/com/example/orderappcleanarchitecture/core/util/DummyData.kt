package com.example.orderappcleanarchitecture.core.util

import com.example.orderappcleanarchitecture.core.domain.model.Product
import com.example.orderappcleanarchitecture.core.domain.model.Vendor

object DummyData {

    val vendors = listOf(
        Vendor(
            3,
            "Paper Factory Ltd",
            listOf(
                Product(7,"Notebook Big",1.45),
                Product(8,"Notebook Medium",1.25),
                Product(9,"Notebook Small",1.05),
                Product(10,"Printer Paper 50x",2.55),
                Product(11,"Printer Paper 100x", 4.55),
            )
        ),
        Vendor(
            4,
            "School Stuff provider",
            listOf(
                Product(12,"Pencil", 1.0),
                Product(13,"Scissor", 2.19),
                Product(14,"Tablet", 149.0),
                Product(15,"School Bag", 21.5),
                Product(16,"Pencil Case", 2.69),
            )
        ),
        Vendor(
            5,
            "Computer Hardware Store",
            listOf(
                Product(17,"Keyboard", 41.0),
                Product(18,"Mouse", 22.19),
                Product(19,"Intel Processor i7", 249.0),
                Product(20,"2x 8GB RAM", 49.1),
                Product(21,"2x 16GB RAM", 89.0),
                Product(22,"2x 32GB RAM", 129.5),
                Product(23,"2x 64GB RAM", 189.0),
            )
        )
    )

}