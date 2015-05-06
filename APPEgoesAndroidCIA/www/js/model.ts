/**
 * Created by orceN on 05.05.2015.
 */

module model {

    export class Order {
        id: number;
        customer: Customer;
        orderItems: OrderItem[];
        deliveryDate: Date;
        status: number;
        salesPerson: string;
        totalAmount: number;
    }

    export class OrderItem {
        id: number;
        article: Article;
        quantity: number;
        price: number;
    }

    export class Article {
        id: number;
        name: string;
        price: number;
        availableStock: number;
        minimalStock: number;
    }

    export class Customer {
        id: number;
        name: string;
    }

    export class UserInfo {
        user: string;
        password: string;
    }

}
