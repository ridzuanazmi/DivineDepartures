export interface RegisterRequest {
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  confirmPassword: string,
};

export interface RegisterResponse {
  message: string;
};

export interface LoginRequest {
  email: string,
  password: string;
};

export interface LoginResponse {
  token: string;
};

export interface Contact {
  contactId: string
  contactName: string
  contactEmail: string
  subject: string
  message: String
};

export interface Shop {
  deceasedName: string;
  block: string;
  plotNumber: number;
  dateOfDeath: string;
  tombstoneHeight: string; // e.g. 10cm
  tombstoneMaterial: string // e.g. granite
  tiles: string; // e.g. white, black, marble, brick
  curvedMosaicTile: string; // e.g. C-159
  topCover: string; // e.g. carpet grass, pebbles
  plant: string; // e.g. jasmine plant
  email?: string // Optional
}

export interface BillingDetails {
  firstName: String;
  lastName: String;
  email: String;
  mobileNumber: number;
  address: string;
  postalCode: String;
}
