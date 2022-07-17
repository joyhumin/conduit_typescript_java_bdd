import React from "react";

window.mockReactComponent = () => jest.fn(
  ({children}) => (<div>
    {children}
  </div>)
);

// iterate through each entry in the library and create a mock for each entry
window.mockAllReactComponents = (library) => Object.fromEntries(
  Object.keys(library).map(k => [k, mockReactComponent()])
);