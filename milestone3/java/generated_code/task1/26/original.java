protected Array createView( Index index) {
    return ArrayObject.factory( dataType, elementType, isVlen, index, storage);
  }