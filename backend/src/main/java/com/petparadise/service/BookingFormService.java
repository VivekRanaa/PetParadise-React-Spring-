package com.petparadise.service;

import com.petparadise.dto.BookingFormDTO;
import com.petparadise.entity.BookingForm;
import com.petparadise.entity.BookingStatus;
import com.petparadise.repository.BookingFormRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingFormService {

    @Autowired
    private BookingFormRepository bookingFormRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Create a new booking
    public BookingFormDTO createBooking(BookingFormDTO bookingFormDTO) {
        BookingForm bookingForm = modelMapper.map(bookingFormDTO, BookingForm.class);
        bookingForm.setStatus(BookingStatus.PENDING);
        BookingForm savedBooking = bookingFormRepository.save(bookingForm);
        return convertToDTO(savedBooking);
    }

    // Get all bookings with pagination
    public Page<BookingFormDTO> getAllBookings(Pageable pageable) {
        Page<BookingForm> bookings = bookingFormRepository.findAll(pageable);
        List<BookingFormDTO> bookingDTOs = bookings.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookingDTOs, pageable, bookings.getTotalElements());
    }

    // Get booking by ID
    public Optional<BookingFormDTO> getBookingById(Long id) {
        Optional<BookingForm> booking = bookingFormRepository.findById(id);
        return booking.map(this::convertToDTO);
    }

    // Update booking
    public BookingFormDTO updateBooking(Long id, BookingFormDTO bookingFormDTO) {
        Optional<BookingForm> existingBooking = bookingFormRepository.findById(id);
        if (existingBooking.isPresent()) {
            BookingForm bookingForm = existingBooking.get();
            updateBookingFields(bookingForm, bookingFormDTO);
            BookingForm updatedBooking = bookingFormRepository.save(bookingForm);
            return convertToDTO(updatedBooking);
        }
        throw new RuntimeException("Booking not found with id: " + id);
    }

    // Update booking status
    public BookingFormDTO updateBookingStatus(Long id, BookingStatus status) {
        Optional<BookingForm> existingBooking = bookingFormRepository.findById(id);
        if (existingBooking.isPresent()) {
            BookingForm bookingForm = existingBooking.get();
            bookingForm.setStatus(status);
            BookingForm updatedBooking = bookingFormRepository.save(bookingForm);
            return convertToDTO(updatedBooking);
        }
        throw new RuntimeException("Booking not found with id: " + id);
    }

    // Delete booking
    public void deleteBooking(Long id) {
        if (bookingFormRepository.existsById(id)) {
            bookingFormRepository.deleteById(id);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    // Get bookings by email
    public List<BookingFormDTO> getBookingsByEmail(String email) {
        List<BookingForm> bookings = bookingFormRepository.findByEmail(email);
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get bookings by service type
    public Page<BookingFormDTO> getBookingsByService(String serviceType, Pageable pageable) {
        Page<BookingForm> bookings = bookingFormRepository.findByFacilities(serviceType, pageable);
        List<BookingFormDTO> bookingDTOs = bookings.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookingDTOs, pageable, bookings.getTotalElements());
    }

    // Get bookings by status
    public Page<BookingFormDTO> getBookingsByStatus(BookingStatus status, Pageable pageable) {
        Page<BookingForm> bookings = bookingFormRepository.findByStatus(status, pageable);
        List<BookingFormDTO> bookingDTOs = bookings.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookingDTOs, pageable, bookings.getTotalElements());
    }

    // Search bookings
    public Page<BookingFormDTO> searchBookings(String searchTerm, Pageable pageable) {
        Page<BookingForm> bookings = bookingFormRepository.findBySearchTerm(searchTerm, pageable);
        List<BookingFormDTO> bookingDTOs = bookings.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookingDTOs, pageable, bookings.getTotalElements());
    }

    // Get recent bookings (last 30 days)
    public List<BookingFormDTO> getRecentBookings() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        List<BookingForm> bookings = bookingFormRepository.findRecentBookings(thirtyDaysAgo);
        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get booking statistics
    public BookingStatistics getBookingStatistics() {
        long totalBookings = bookingFormRepository.count();
        long pendingBookings = bookingFormRepository.countByStatus(BookingStatus.PENDING);
        long confirmedBookings = bookingFormRepository.countByStatus(BookingStatus.CONFIRMED);
        long completedBookings = bookingFormRepository.countByStatus(BookingStatus.COMPLETED);
        long cancelledBookings = bookingFormRepository.countByStatus(BookingStatus.CANCELLED);

        long boardingBookings = bookingFormRepository.countByFacilities("Boarding");
        long groomingBookings = bookingFormRepository.countByFacilities("Grooming");
        long poolBookings = bookingFormRepository.countByFacilities("Pool");
        long petShopBookings = bookingFormRepository.countByFacilities("PetShop");

        return new BookingStatistics(
                totalBookings, pendingBookings, confirmedBookings, completedBookings, cancelledBookings,
                boardingBookings, groomingBookings, poolBookings, petShopBookings
        );
    }

    // Helper method to convert Entity to DTO
    private BookingFormDTO convertToDTO(BookingForm bookingForm) {
        BookingFormDTO dto = modelMapper.map(bookingForm, BookingFormDTO.class);
        if (bookingForm.getCreatedAt() != null) {
            dto.setCreatedAt(bookingForm.getCreatedAt().format(formatter));
        }
        if (bookingForm.getUpdatedAt() != null) {
            dto.setUpdatedAt(bookingForm.getUpdatedAt().format(formatter));
        }
        if (bookingForm.getStatus() != null) {
            dto.setStatus(bookingForm.getStatus().toString());
        }
        return dto;
    }

    // Helper method to update booking fields
    private void updateBookingFields(BookingForm existingBooking, BookingFormDTO bookingFormDTO) {
        existingBooking.setFirst_Name(bookingFormDTO.getFirst_Name());
        existingBooking.setLast_Name(bookingFormDTO.getLast_Name());
        existingBooking.setEmail(bookingFormDTO.getEmail());
        existingBooking.setMobile_No(bookingFormDTO.getMobile_No());
        existingBooking.setCountry(bookingFormDTO.getCountry());
        existingBooking.setState(bookingFormDTO.getState());
        existingBooking.setPincode(bookingFormDTO.getPincode());
        existingBooking.setFacilities(bookingFormDTO.getFacilities());
        existingBooking.setNotes(bookingFormDTO.getNotes());
    }

    // Inner class for booking statistics
    public static class BookingStatistics {
        private final long totalBookings;
        private final long pendingBookings;
        private final long confirmedBookings;
        private final long completedBookings;
        private final long cancelledBookings;
        private final long boardingBookings;
        private final long groomingBookings;
        private final long poolBookings;
        private final long petShopBookings;

        public BookingStatistics(long totalBookings, long pendingBookings, long confirmedBookings,
                               long completedBookings, long cancelledBookings, long boardingBookings,
                               long groomingBookings, long poolBookings, long petShopBookings) {
            this.totalBookings = totalBookings;
            this.pendingBookings = pendingBookings;
            this.confirmedBookings = confirmedBookings;
            this.completedBookings = completedBookings;
            this.cancelledBookings = cancelledBookings;
            this.boardingBookings = boardingBookings;
            this.groomingBookings = groomingBookings;
            this.poolBookings = poolBookings;
            this.petShopBookings = petShopBookings;
        }

        // Getters
        public long getTotalBookings() { return totalBookings; }
        public long getPendingBookings() { return pendingBookings; }
        public long getConfirmedBookings() { return confirmedBookings; }
        public long getCompletedBookings() { return completedBookings; }
        public long getCancelledBookings() { return cancelledBookings; }
        public long getBoardingBookings() { return boardingBookings; }
        public long getGroomingBookings() { return groomingBookings; }
        public long getPoolBookings() { return poolBookings; }
        public long getPetShopBookings() { return petShopBookings; }
    }
}
