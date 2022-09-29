export interface User {
  username: string;
  email: string;
  bio: string;
  token: string;
  image: string;
}
export interface UserResponse{
  user: User;
}

export const EMPTY_USER: User = {
  username: "",
  email: "",
  bio: "",
  token: "",
  image: "",
};