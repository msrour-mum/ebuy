export class Product {

  public id: number;
  public name: string;
  public price: number;
  public shortDescription: string;
  public imageUrl: string;

  constructor(id: number,
              name: string,
              price: number,
              shortDescription: string,
              imageUrl: string) {

    this.id = id;
    this.name = name;
    this.price = price;
    this.shortDescription = shortDescription;
    this.imageUrl = imageUrl;
  }
}
