export class Product {

  public id: number;
  public name: string;
  public price: number;
  public shortDescription: string;
  public imageUrl: string;
  public promotionPrice: number;

  constructor(id: number,
              name: string,
              price: number,
              shortDescription: string,
              imageUrl: string,
              promotionPrice: number) {

    this.id = id;
    this.name = name;
    this.price = price;
    this.shortDescription = shortDescription;
    this.imageUrl = imageUrl;
    this.promotionPrice = promotionPrice;
  }
}
