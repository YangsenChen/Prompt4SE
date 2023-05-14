# def sll(sig, howMany) -> RtlSignalBase:
#     "Logical shift left"
#     width = sig._dtype.bit_length()
#     return sig[(width - howMany):]._concat(vec(0, howMany))

from typing import Union
from myhdl import Signal, intbv, always_comb, Simulation, bin, traceSignals, instance, delay

class RtlSignalBase:
    pass

class vec(RtlSignalBase):
    def __init__(self, value: int, width: int):
        self._dtype = intbv(value, min=0, max=2 ** width)
        self.width = width

    def __getitem__(self, index: Union[int, slice]):
        if isinstance(index, int):
            return (self._dtype >> index) & 0x1
        else:
            start, stop, step = index.indices(self.width)
            return (self._dtype >> start) & ((1 << (stop - start)) - 1)

    def _concat(self, other):
        return vec((self._dtype << other.width) | other._dtype, self.width + other.width)

def sll(sig: vec, howMany: int) -> RtlSignalBase:
    "Logical shift left"
    width = sig._dtype.bit_length()
    return sig[(width - howMany):]._concat(vec(0, howMany))

def main():
    sig = vec(0b1011, 4)  # Example 4-bit signal with value 0b1011
    shifted = sll(sig, 2)  # Shift left by 2 positions
    print(f"Original signal: {bin(sig._dtype)}")
    print(f"Shifted signal: {bin(shifted._dtype)}")

if __name__ == "__main__":
    main()