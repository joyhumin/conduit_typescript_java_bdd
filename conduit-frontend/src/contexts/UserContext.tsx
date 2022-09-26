import React, { useState } from "react";
import { EMPTY_USER, User } from "../types";

type UserProviderProps = { children: React.ReactNode; }

interface UserContext {
  user: User,
  setUser: (user: User) => void
}

const initialState = {
  user: EMPTY_USER,
  setUser: () => { /* placeholder method */
  },
};


const UserStateContext = React.createContext<UserContext | undefined>(initialState);

export function useUserContext() {
  const context = React.useContext(UserStateContext);

  if (context === undefined) {
    throw new Error("user must be defined first");
  }
  return context;
}

export function UserProvider({ children }: UserProviderProps) {
  console.log("**********UserProvider rendered**********");
  const [user, setUser] = useState<User>();
  const value = { user, setUser } as UserContext;

  return (
    <UserStateContext.Provider value={value}>
      {children}
    </UserStateContext.Provider>
  );
}