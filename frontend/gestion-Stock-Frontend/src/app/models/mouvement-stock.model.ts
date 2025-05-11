import { Entrepot } from "./entrepot.model";
import { Produit } from "./produit.model";

export interface MouvementStock {
  id: number;
  produit: Produit;
  type: 'ENTREE' | 'SORTIE';
  quantite: number;
  date: Date;
  entrepot: Entrepot;
}