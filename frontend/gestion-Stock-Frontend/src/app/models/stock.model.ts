import { Entrepot } from "./entrepot.model";
import { Produit } from "./produit.model";

export interface Stock {
  id: number;
  produit: Produit;
  quantite: number;
  entrepot: Entrepot;
  seuilAlerte: number;
}