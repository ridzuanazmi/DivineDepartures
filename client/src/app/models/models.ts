export interface RegisterRequest {
  fullName: string,
  phoneNumber: string,
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

export interface User {
  fullName: string
  phoneNumber: string
  email: string
};
export interface Account {
  userId: number
  fullName: string
  phoneNumber: string
  email: string
  createdDate: string
  role: string
};

export interface Contact {
  contactId: string
  contactName: string
  contactEmail: string
  phoneNumber: string
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
  fullName?: string // Optional
  phoneNumber?: string // Optional
};

export interface BillingDetails {
  firstName: String;
  lastName: String;
  email: String;
  mobileNumber: number;
  address: string;
  postalCode: String;
};

export interface StripeId{
  sessionId: string
};
