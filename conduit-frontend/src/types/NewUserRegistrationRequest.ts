export interface NewUserRegistrationRequest {
  user: {
    username: string;
    email: string;
    password: string;
  }
}

export const EMPTY_REGISTRATION: NewUserRegistrationRequest = {
  user: {
    username: "",
    email: "",
    password: "",
  }
};

