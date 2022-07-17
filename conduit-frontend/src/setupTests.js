// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom
import '@testing-library/jest-dom';
import "chance";
import object from "chance-object";
import "./mockReactComponents";


const root = document.createElement("div");
root.setAttribute("id", "root");
document.documentElement.append(root);

chance.mixin({object}); //TODO: what does this do?

const catchAsyncError = async (fn) => {
  try {
    await fn();
  } catch (e) {
    return e;
  }
};

global.catchAsyncError = catchAsyncError;
