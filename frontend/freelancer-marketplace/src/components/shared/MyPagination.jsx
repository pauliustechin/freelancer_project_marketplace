import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';

export default function MyPagination() {
  return (
    <Stack spacing={2}>
      <Pagination 
        count={10} 
        sx={{
          "& .Mui-selected": {
            backgroundColor: "#44a5f5",
            color: "white",
          },
          "& .Mui-selected:hover": {
            backgroundColor: "#44a5f5",
          },
        }}
      />
    </Stack>
  );
}