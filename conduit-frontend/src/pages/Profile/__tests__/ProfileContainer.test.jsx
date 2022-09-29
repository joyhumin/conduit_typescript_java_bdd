import React from "react";
import { create } from "react-test-renderer";
import { useProfile } from "../../../hooks";
import { ProfileContainer } from "../ProfileContainer";
import { Profile } from "../Profile";

jest.mock("../../../hooks");
jest.mock("../Profile", () => mockAllReactComponents(jest.requireActual("../Profile")));

describe("ProfileContainer", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("Can retrieve Profile content", () => {
    const example = chance.string();

    // Given
    useProfile.mockReturnValueOnce({ data: { example } });

    // When
    const actual = create(<ProfileContainer />).root.findByType(Profile);

    // Then
    expect(useProfile).toBeCalledWith("Profile");
    expect(actual.props.content).toBe(example);
  });

  test("Can not retrieve Profile content", () => {
    // Given
    useProfile.mockReturnValueOnce({ });

    // When
    const actual = create(<ProfileContainer />).root.findByType(Profile);

    // Then
    expect(useProfile).toBeCalledWith("Profile");
    expect(actual.props.content).toBe("");
  });
});
