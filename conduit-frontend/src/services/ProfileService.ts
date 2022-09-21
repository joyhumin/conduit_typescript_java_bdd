import { ProfileState } from "../types";

export const retrieveProfile = async (content: string): Promise<ProfileState> =>
  fetch(`/assets/data/${content}.json`, {
    headers: new Headers({ "Content-Type": "application/json; charset=utf-8" }),
  }).then((response) => {
    if (!response.ok) {
      return response.json().then(({ error }) => {
        throw new Error(error);
      });
    }
    return response.json();
  });
