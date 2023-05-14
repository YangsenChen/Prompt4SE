public int peakNextBits(int n) throws IOException {
   int cnt = 0;
   int[] bits = new int[16];
   
   while(nBit < 8 && cnt < 16) {
      bits[cnt++] = (curByte >> (7 - nBit)) & 0x1;
      nBit++;
   }
   
   if (n > nBit) {
      advance();
      if (curByte == -1) {
         return -1;
      }
      
      nBit = 0;
      
      while(cnt < 16) {
         bits[cnt++] = (curByte >> (7 - nBit)) & 0x1;
         nBit++;
         
         if (nBit == 8) {
            advance();
            if (curByte == -1) {
               return -1;
            }
            
            nBit = 0;
         }
      }
   }
   
   int result = 0;
   for (int i = 0; i < n; i++) {
      result <<= 1;
      result |= bits[i];
   }

   return result;
}