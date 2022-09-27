import { UserResponse } from "../types";
import env from "../environment";

// fixme: parameter type should not be any
export const sendNewUserRequest = async (content: any): Promise<UserResponse> =>
  fetch(`${env.REACT_APP_API_ENDPOINT}/users`,
    {
      method: "POST",
      body: JSON.stringify(content),
      headers: new Headers({ "Content-Type": "application/json; charset=utf-8" }),
    }
  ).then((response) => {
    if (!response.ok) {
      return response.json().then(({ error }) => {
        throw new Error(error);
      });
    }
    return response.json();
  });

export const loginRequest = async (content: any): Promise<UserResponse> =>
  fetch(`${env.REACT_APP_API_ENDPOINT}/users/login`,
    {
      method: "POST",
      body: JSON.stringify(content),
      headers: new Headers({ "Content-Type": "application/json; charset=utf-8" }),
    }
  ).then((response) => {
    if (!response.ok) {
      return response.json().then(({ error }) => {
        throw new Error(error);
      });
    }
    return response.json();
  });
