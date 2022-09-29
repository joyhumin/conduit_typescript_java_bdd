import React from "react";
import { create } from "react-test-renderer";
import { Box } from "@mui/material";
import { NavMenuItem } from "../NavMenuItem";

jest.mock("@mui/material", () => mockAllReactComponents(jest.requireActual("@mui/material")));

describe("NavMenuItem", () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  test("renders without error", () => {
    // Given
    const content = chance.string();

    // When
    const actual = create(<NavMenuItem itemName={content} />).root.findByType(Box);

    // Then
    expect(actual.props.children).toEqual(content);
  });
});
